/*
 * *
 *  * Created by estiv on 15/07/21, 4:19 p. m.
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 15/07/21, 4:19 p. m.
 *
 */

package com.alp.app.data.model

import com.google.gson.annotations.SerializedName

data class ChangeLikeModel(
    @SerializedName("response") var response: Int
)