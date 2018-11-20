package cespresso.gmail.com.todo.security.security_model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class FirebaseUserDetails(
        var email:String = "",
        var id:String = ""
):UserDetails {
    companion object {
        val versionID = 1L
    }
    // UserDetailsの必須項目
    val enabled = true
    val credentialsNonExpired = true // TODO 書き換え可能にする
    val accountNonLocked = true //
    val accountNonExpired = true
    val password = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null //何か定義
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun getUsername(): String {
        accountNonExpired
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired //TODO ここはFirebaseに結果によって書き換えたい
    }

    override fun getPassword(): String? {
        return password // これをこちらで管理することはない
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked
    }
}