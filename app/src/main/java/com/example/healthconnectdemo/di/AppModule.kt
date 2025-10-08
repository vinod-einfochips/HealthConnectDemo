package com.example.healthconnectdemo.di

import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import com.example.healthconnectdemo.healthconnect.HealthConnectManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHealthConnectClient(
        @ApplicationContext context: Context,
    ): HealthConnectClient {
        return HealthConnectClient.getOrCreate(context)
    }

    @Provides
    @Singleton
    fun provideHealthConnectManager(
        healthConnectClient: HealthConnectClient,
        @ApplicationContext context: Context,
    ): HealthConnectManager {
        return HealthConnectManager(healthConnectClient, context)
    }
}
