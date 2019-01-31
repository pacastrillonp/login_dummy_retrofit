package co.test.logindummiemvvm.model

import com.google.gson.annotations.SerializedName

data class Account(@SerializedName("BaseDomain") val baseDomain: String, @SerializedName("LegalName") val legalName: String)