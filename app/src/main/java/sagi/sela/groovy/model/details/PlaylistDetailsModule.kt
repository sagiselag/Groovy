package sagi.sela.groovy.model.details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class PlaylistDetailsModule {

    @Provides
    fun playlistDetailsAPI(retrofit: Retrofit) = retrofit.create(PlaylistDetailsAPI::class.java)

    // we can ignore the below provide annotation for the retrofit implementation
    // because we are already have it in the playlistModule file

//    @Provides
//    fun retrofit() = Retrofit.Builder()
//            .baseUrl("http://192.168.100.1:3000/")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

}