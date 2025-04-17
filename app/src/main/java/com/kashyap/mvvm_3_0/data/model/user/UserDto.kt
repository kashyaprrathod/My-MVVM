package com.kashyap.mvvm_3_0.data.model.user

import com.google.gson.annotations.SerializedName
import com.kashyap.mvvm_3_0.domain.model.UserBean

data class UserDto(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("first_name")
    var firstName: String = "",

    @SerializedName("email")
    var email: String = "",

    @SerializedName("avatar")
    var profilePhoto: String = "",

    @SerializedName("country_id")
    var countryId: Int = 0,

    @SerializedName("notification_status")
    var notificationEnabled: Boolean = true,

    @SerializedName("country_name")
    var countryName: String = "",

    @SerializedName("encryption_code")
    var encryptionCode: String = "",

    @SerializedName("delete_account_url")
    var deleteAccountUrl: String = "",

    @SerializedName("account_delete_requested_at")
    var accountDeleteRequestedAt: String = "",

    @SerializedName("room_no")
    var roomNo: Int = 0,
) {
    fun map(): UserBean {
        return UserBean(
            authToken = ""
        )
    }
}