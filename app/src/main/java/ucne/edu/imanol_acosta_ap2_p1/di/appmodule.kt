package ucne.edu.imanol_acosta_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ucne.edu.imanol_acosta_ap2_p1.data.Repository.BorrameRepositoryImpl
import ucne.edu.imanol_acosta_ap2_p1.data.local.dao.BorrameDao
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.BorrameRepository
import ucne.edu.imanol_acosta_ap2_p1.data.database.BorrameDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideBorrameDB(@ApplicationContext appContext: Context): BorrameDatabase {
        return Room.databaseBuilder(
            appContext,
            BorrameDatabase::class.java,
            "BorrameDB"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBorrameDao(db: BorrameDatabase): BorrameDao {
        return db.borrameDao()
    }

    @Provides
    @Singleton
    fun provideBorrameRepository(impl: BorrameRepositoryImpl): BorrameRepository {
        return impl
    }
}