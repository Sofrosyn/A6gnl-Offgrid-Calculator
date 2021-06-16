package com.sofrosyn.a6gnlsolarcalculator.Utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


//singleton pattern
object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser





}