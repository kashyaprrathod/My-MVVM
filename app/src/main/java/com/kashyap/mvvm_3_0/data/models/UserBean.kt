import com.google.gson.annotations.SerializedName

data class UserBean(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    var firstName: String = "",

    @SerializedName("email")
    var email: String = "",

    @SerializedName("profile_picture")
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

    @SerializedName("auth")
    var authentication: Authentication = Authentication("", 0, "", ""),

    @SerializedName("item")
    val itemBean: ArrayList<ItemBean> = ArrayList(),
)

data class Authentication(
    @SerializedName("access_token")
    var accessToken: String = "",

    @SerializedName("expires_in")
    var expiresIn: Int = 0,

    @SerializedName("refresh_token")
    var refreshToken: String = "",

    @SerializedName("token_type")
    var tokenType: String = ""
)

data class ItemBean(
    @SerializedName("name")
    var name: String = "",
)