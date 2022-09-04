package com.example.qrholder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule{

    @Provides
    fun provideMockClass(
        mockClass1: MockSubClass1,
        mockClass2: MockSubClass2,
        mockClass3: MockSubClass3
    ) : MockClass{
        return MockClass(mockClass1,mockClass2,mockClass3)
    }

    @Provides
    fun provideSubMock1() = MockSubClass1()
    @Provides
    fun provideSubMock2() = MockSubClass2()
    @Provides
    fun provideSubMock3() = MockSubClass3()

}