package com.example.data_local.utils

import android.content.Context
import android.os.Build
import android.util.Base64
import com.example.data_local.model.Storable
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class SQLCipherHelper (
    private val context: Context
) {
    private lateinit var rawByteKey: ByteArray
    private lateinit var dbCharKey: String
    private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

    /**
     * Generates a random 32 byte key.
     *
     * @return a byte array containing random values
     */
    private fun generateRandomKey(): ByteArray =
        ByteArray(32).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecureRandom.getInstanceStrong().nextBytes(this)
            } else {
                SecureRandom().nextBytes(this)
            }
        }

    /**
     * Extension function that converts a ByteArray to a hex encoded String
     */
    private fun ByteArray.toHex(): String {
        val result = StringBuilder()
        forEach {
            val octet = it.toInt()
            val firstIndex = (octet and 0xF0).ushr(4)
            val secondIndex = octet and 0x0F
            result.append(HEX_CHARS[firstIndex])
            result.append(HEX_CHARS[secondIndex])
        }
        return result.toString()
    }

    /**
     * Generates a new database key.
     */
    private fun createNewKey() {
        // This is the raw key that we'll be encrypting + storing
        rawByteKey = generateRandomKey()
        // This is the key that will be used by Room
        dbCharKey = rawByteKey.toHex()
    }

    private fun persistRawKey(userPasscode: CharArray) {
        val storable = toStorable(rawByteKey, userPasscode)
        saveToPrefs(storable)
    }

    /**
     * Returns a [Storable] instance with the db key encrypted using PBE.
     *
     * @param rawDbKey the raw database key
     * @param userPasscode the user's passcode
     * @return storable instance
     */
    private fun toStorable(rawDbKey: ByteArray, userPasscode: CharArray): Storable {
        // Generate a random 8 byte salt
        val salt = ByteArray(8).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecureRandom.getInstanceStrong().nextBytes(this)
            } else {
                SecureRandom().nextBytes(this)
            }
        }
        val secret: SecretKey = generateSecretKey(userPasscode, salt)

        // Now encrypt the database key with PBE
        val cipher: Cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secret)
        val iv: ByteArray = cipher.iv
        val ciphertext: ByteArray = cipher.doFinal(rawDbKey)

        // Return the IV and CipherText which can be stored to disk
        return Storable(
            Base64.encodeToString(iv, Base64.DEFAULT),
            Base64.encodeToString(ciphertext, Base64.DEFAULT),
            Base64.encodeToString(salt, Base64.DEFAULT)
        )
    }

    private fun generateSecretKey(passcode: CharArray, salt: ByteArray): SecretKey {
        // Initialize PBE with password
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2withHmacSHA1And8BIT")
        val spec: KeySpec = PBEKeySpec(passcode, salt, 65536, 256)
        val tmp: SecretKey = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, "AES")
    }

    /**
     * Save the storable instance to preferences.
     *
     * @param storable a storable instance
     */
    private fun saveToPrefs(storable: Storable) {
        val serialized = Gson().toJson(storable)
        val prefs = context.getSharedPreferences("database",
            Context.MODE_PRIVATE)
        prefs.edit().putString("key", serialized).apply()
    }

    /**
     * Retrieves the [Storable] instance from prefs.
     *
     * @param context the caller's context
     * @return the storable instance
     */
    private fun getStorable(context: Context): Storable? {
        val prefs = context.getSharedPreferences("database",
            Context.MODE_PRIVATE)
        val serialized = prefs.getString("key", null)
        if (serialized.isNullOrBlank()) {
            return null
        }

        return try {
            Gson().fromJson(serialized,
                object: TypeToken<Storable>() {}.type)
        } catch (ex: JsonSyntaxException) {
            null
        }
    }

    /**
     * Decrypts the [Storable] instance using the [passcode].
     *
     * @pararm passcode the user's passcode
     * @param storable the storable instance previously saved with [saveToPrefs]
     * @return the raw byte key previously generated with [generateRandomKey]
     */
    private fun getRawByteKey(passcode: CharArray, storable: Storable): ByteArray {
        val aesWrappedKey = Base64.decode(storable.key, Base64.DEFAULT)
        val iv = Base64.decode(storable.iv, Base64.DEFAULT)
        val salt = Base64.decode(storable.salt, Base64.DEFAULT)
        val secret: SecretKey = generateSecretKey(passcode, salt)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, secret, IvParameterSpec(iv))
        return cipher.doFinal(aesWrappedKey)
    }

    /**
     * Returns the database key suitable for using with Room.
     *
     * @param passcode the user's passcode
     * @param context the caller's context
     */
    fun getCharKey(passcode: CharArray): String {
        if (!this::dbCharKey.isInitialized) {
            initKey(passcode)
        }
        return dbCharKey ?: error("Failed to decrypt database key")
    }

    private fun initKey(passcode: CharArray) {
        val storable = getStorable(context)
        if (storable == null) {
            createNewKey()
            persistRawKey(passcode)
        } else {
            rawByteKey = getRawByteKey(passcode, storable)
            dbCharKey = rawByteKey.toHex()
        }
    }
}

