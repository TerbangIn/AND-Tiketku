package com.ketarketir.tiketkuioflight.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.user.DataPostGenerateOtp
import com.ketarketir.tiketkuioflight.model.user.DataPostUserVerify
import com.ketarketir.tiketkuioflight.model.user.DataResponseGenerateOtp
import com.ketarketir.tiketkuioflight.model.user.DataResponseResetPassword
import com.ketarketir.tiketkuioflight.model.user.DataResponseVerifyUser
import com.ketarketir.tiketkuioflight.model.user.DataResetPassword
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

    private val _otpSent = MutableLiveData<Boolean>()
    val otpSent get() = _otpSent

    private val _otpVerified = MutableLiveData<Boolean>()
    val otpVerified get() = _otpVerified

    private val _passwordSaved = MutableLiveData<Boolean>()
    val passwordSaved get() = _passwordSaved

    fun sendOTP(email: String) {
        val generateOtpRequest = DataPostGenerateOtp(email)
        val call = apiService.generateOtp(generateOtpRequest)

        call.enqueue(object : Callback<DataResponseGenerateOtp> {
            override fun onResponse(
                call: Call<DataResponseGenerateOtp>,
                response: Response<DataResponseGenerateOtp>
            ) {
                if (response.isSuccessful) {
                    // Pengiriman OTP sukses
                    _otpSent.value = true
                } else {
                    // Pengiriman OTP gagal
                    _otpSent.value = false
                }
            }

            override fun onFailure(call: Call<DataResponseGenerateOtp>, t: Throwable) {
                // Pengiriman OTP gagal karena error jaringan atau masalah lainnya
                _otpSent.value = false
            }
        })
    }

    fun verifyOTP(email: String, otp: String) {
        val verifyOtpRequest = DataPostUserVerify(email, otp)
        val call = apiService.verifyUser(verifyOtpRequest)

        call.enqueue(object : Callback<DataResponseVerifyUser> {
            override fun onResponse(
                call: Call<DataResponseVerifyUser>,
                response: Response<DataResponseVerifyUser>
            ) {
                if (response.isSuccessful) {
                    // Verifikasi OTP sukses
                    _otpVerified.value = true
                } else {
                    // Verifikasi OTP gagal
                    _otpVerified.value = false
                }
            }

            override fun onFailure(call: Call<DataResponseVerifyUser>, t: Throwable) {
                // Verifikasi OTP gagal karena error jaringan atau masalah lainnya
                _otpVerified.value = false
            }
        })
    }

    fun resetPassword(email: String, otp: String, password: String, confirmPassword: String) {
        if (password == confirmPassword) {
            val resetPasswordRequest = DataResetPassword(email, otp, password, confirmPassword)
            val call = apiService.resetPassword(resetPasswordRequest)

            call.enqueue(object : Callback<DataResponseResetPassword> {
                override fun onResponse(
                    call: Call<DataResponseResetPassword>,
                    response: Response<DataResponseResetPassword>
                ) {
                    if (response.isSuccessful) {
                        // Penyimpanan password baru sukses
                        _passwordSaved.value = true
                    } else {
                        // Penyimpanan password baru gagal
                        _passwordSaved.value = false
                    }
                }

                override fun onFailure(call: Call<DataResponseResetPassword>, t: Throwable) {
                    // Penyimpanan password baru gagal karena error jaringan atau masalah lainnya
                    _passwordSaved.value = false
                }
            })
        } else {
            // Password baru dan konfirmasi password tidak sesuai
            _passwordSaved.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Bersihkan sumber daya atau lakukan tindakan yang diperlukan saat ViewModel dihapus
    }
}
