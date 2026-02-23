package com.example.tanteo

import android.os.Parcel
import android.os.Parcelable

data class TeamPoints(var de1: Int, var de2: Int, var de3: Int) : Parcelable {
    val total get() = de1 + 2*de2 + 3*de3

    constructor(parcel : Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun describeContents(): Int {
        return 0
    }

    // Escribimos los elementos de la clase en el parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(de1)
        dest.writeInt(de2)
        dest.writeInt(de3)
    }

    // Hay que meter mas funciones
    companion object CREATOR : Parcelable.Creator<TeamPoints> {
        override fun createFromParcel(parcel: Parcel): TeamPoints {
            return TeamPoints(parcel)
        }

        override fun newArray(size: Int): Array<out TeamPoints?> {
            return arrayOfNulls(size)
        }

    }

}
