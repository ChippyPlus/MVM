package org.example.kvmInternals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.toGeneralRegisterType
import org.example.data.registers.enumIdenifiers.toReturnRegisterType
import org.example.data.registers.enumIdenifiers.toSystemRegisterType
import org.example.generalRegisters
import org.example.returnRegisters
import org.example.systemRegisters

fun DataTransfer.lit(Source: SuperRegisterType, value: Long) {
    when (Source.toString()[0]) {
        'G' -> {
            generalRegisters.write(Source.toGeneralRegisterType(), value)
        }

        'S' -> {
            systemRegisters.write(Source.toSystemRegisterType(), value)
        }

        'R' -> {
            returnRegisters.write(Source.toReturnRegisterType(), value)
        }
    }

}