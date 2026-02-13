package ucne.edu.imanol_acosta_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ucne.edu.imanol_acosta_ap2_p1.data.local.dao.CervezaDao
import ucne.edu.imanol_acosta_ap2_p1.data.database.CervezaDb
import ucne.edu.imanol_acosta_ap2_p1.data.Repository.CervezaRepositoryImpl
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.CervezaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCervezaDb(@ApplicationContext context: Context): CervezaDb {
        return Room.databaseBuilder(
            context,
            CervezaDb::class.java,
            "CervezaDb"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCervezaDao(cervezaDb: CervezaDb): CervezaDao {
        return cervezaDb.cervezaDao()
    }

    @Provides
    @Singleton
    fun provideCervezaRepository(cervezaRepositoryImpl: CervezaRepositoryImpl): CervezaRepository {
        return cervezaRepositoryImpl
    }
}