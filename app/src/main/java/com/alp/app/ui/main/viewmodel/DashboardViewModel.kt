/*
 * *
 *  * Created by estiv on 3/07/21 09:56 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 29/06/21 11:02 PM
 *
 */

package com.alp.app.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.alp.app.data.repository.DashboardRepository
import com.alp.app.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


class DashboardViewModel @ViewModelInject constructor(private val dashboardRepository: DashboardRepository) : ViewModel() {

    fun getCategories() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getSlider() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getSlider()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getDetailTopic(id_topic: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getDetailTopic(id_topic)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getComments(id_user: Int, id_detail_topic: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getComments(id_user, id_detail_topic)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun editComment(id_comment: Int, comment: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.editComment(id_comment, comment)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun editReply(id_reply: Int, reply: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.editReply(id_reply, reply)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun deleteComment(id_comment: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.deleteComment(id_comment)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun deleteReply(id_reply: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.deleteReply(id_reply)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun changeLike(active: Int, id_comment: Int, id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.changeLike(active, id_comment, id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun changeLikeReply(active: Int, id_comment: Int, id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.changeLikeReply(active, id_comment, id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getReplies(id_user: Int, id_comment: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getReplies(id_user, id_comment)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }


    fun searchCourses(search: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.searchCourses(search)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getCourses(id_category: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getCourses(id_category)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getCoursesHome() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getCoursesHome()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setResetPassword(password: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setResetPassword(password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setPassword(current_password: String, new_password: String, id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setPassword(current_password, new_password, id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setSignIn(email: String, password: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setSignIn(email, password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setToken(id_user: Int, id_token: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setToken(id_user, id_token)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setCertificate(id_user : Int ,id_course: Int, id_completed: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setCertificate(id_user, id_course, id_completed)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getReview(id_course: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getReview(id_course)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }



    fun setProgress(id_enabled : Int ,id_course_temary: Int, id_course: Int, id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setProgress(id_enabled, id_course_temary, id_course, id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun getDetailsTemary(id_course_temary : Int, id_course: Int, id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getDetailsTemary(id_course_temary, id_course, id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setSignUp(names: String, last_names: String, email: String, password: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setSignUp(names,last_names,email,password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }



    fun getInfoProfile(id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getInfoProfile(id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun addComment(id_user: Int, id_detail_topic: Int, comment: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.addComment(id_user, id_detail_topic, comment)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun addReply(id_user: Int, id_comment: Int, comment: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.addReply(id_user, id_comment, comment)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

    fun setInfoProfile(id_user: Int, name: String, image: String, last_names: String, email: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.setInfoProfile(id_user, name, image, last_names, email)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }


    fun getCertificate(id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getCertificate(id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }


    fun getTopics(id_course: Int, id_user: Int) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dashboardRepository.getTopics(id_course, id_user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Ha ocurrido un error"))
        }
    }

}