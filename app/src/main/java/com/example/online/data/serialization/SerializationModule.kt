package com.example.online.data.serialization

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import java.util.Date

val applicationSerializationModule = SerializersModule {
    contextual(DateSerializer)
} 