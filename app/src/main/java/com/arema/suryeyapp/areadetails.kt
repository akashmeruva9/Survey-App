package com.arema.suryeyapp

class areadetails {

    var Numberofhall : String? = null
    var NumberofRoom : String? = null
    var NumberofKitchen : String? = null
    var NumberofToilet : String? = null
    var TotalArea : String? = null

    constructor(
        Numberofhall: String?,
        NumberofRoom: String?,
        NumberofKitchen: String?,
        NumberofToilet: String?,
        TotalArea: String?
    ) {
        this.Numberofhall = Numberofhall
        this.NumberofRoom = NumberofRoom
        this.NumberofKitchen = NumberofKitchen
        this.NumberofToilet = NumberofToilet
        this.TotalArea = TotalArea
    }

    constructor()

}