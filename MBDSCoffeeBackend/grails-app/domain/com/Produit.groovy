package com

import security.User

class Produit {

    String nom
    int nombre
    String type

    static hasOne = [user : User]

    static constraints = {
    }
}
