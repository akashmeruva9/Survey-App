package com.arema.suryeyapp

class cardetails {


    var model : String? = null
    var odometer : String? = null
    var enginenumber : String? = null
    var color : String? = null
    var chasis : String? = null
    var condidtionofbody : String? = null
    var conditionofengine : String? = null
    var conditionoftyre : String? = null
    var remarks : String? = null
    var location : String? = null

    constructor(
        model: String?,
        odometer: String?,
        enginenumber: String?,
        color: String?,
        chasis: String?,
        condidtionofbody: String?,
        conditionofengine: String?,
        conditionoftyre: String?,
        remarks: String?,
        location: String?
    ) {
        this.model = model
        this.odometer = odometer
        this.enginenumber = enginenumber
        this.color = color
        this.chasis = chasis
        this.condidtionofbody = condidtionofbody
        this.conditionofengine = conditionofengine
        this.conditionoftyre = conditionoftyre
        this.remarks = remarks
        this.location = location
    }

    constructor()

}