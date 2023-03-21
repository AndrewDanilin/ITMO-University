package com.andrewdanilin.homework5.multipart

class Field(val name: String, val data: String, val type: Type)

enum class Type {
    TEXT, JSON, FILE
}