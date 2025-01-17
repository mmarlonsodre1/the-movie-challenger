package com.example.data_local.utils

/**
 * Container for everything needed for decrypting the database.
 *
 * @param iv initialization vector
 * @param key encrypted database key
 * @param salt cryptographic salt
 */
data class Storable(val iv: String, val key: String, val salt: String)