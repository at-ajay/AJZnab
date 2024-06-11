package com.aj.znab.auth.util

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.aj.znab.R
import com.aj.znab.core.util.Task
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID

class GoogleAuthClient(private val context: Context) {

    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.create(context)

    suspend fun signIn() : Task<String> {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(context.getString(R.string.google_web_client_id))
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .setNonce(getNonceString())
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(context = context, request = request)
            val googleIdToken = GoogleIdTokenCredential.createFrom(result.credential.data)
            val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken.idToken, null)

            val userId = auth.signInWithCredential(googleCredentials).await().user?.uid
            return if (userId != null) Task.Success(data = userId) else Task.Failure(errorMessage = context.getString(R.string.err_common))
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            return Task.Failure(errorMessage = context.getString(R.string.err_common))
        } catch (e: FirebaseAuthInvalidUserException) {
            e.printStackTrace()
            return Task.Failure(errorMessage = context.getString(R.string.err_invalid_user))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            e.printStackTrace()
            return Task.Failure(errorMessage = context.getString(R.string.err_invalid_user))
        } catch (e: FirebaseAuthUserCollisionException) {
            e.printStackTrace()
            return Task.Failure(errorMessage = context.getString(R.string.err_user_already_exists))
        }
    }

    suspend fun signOut() {
        credentialManager.clearCredentialState(request = ClearCredentialStateRequest())
        auth.signOut()
    }

    private fun getNonceString(): String {
        val rawNonce = UUID.randomUUID().toString()
        val nonceBytes = rawNonce.toByteArray()
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val digest = messageDigest.digest(nonceBytes)
        val hashedNonce = digest.fold("") {_, it -> "%02x".format(it)}

        return hashedNonce
    }

}