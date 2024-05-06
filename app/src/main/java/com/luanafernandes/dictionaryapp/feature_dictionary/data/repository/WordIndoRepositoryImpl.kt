package com.luanafernandes.dictionaryapp.feature_dictionary.data.repository

import android.net.http.HttpException
import com.luanafernandes.dictionaryapp.core.util.Resource
import com.luanafernandes.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.luanafernandes.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.luanafernandes.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.luanafernandes.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordIndoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>  = flow {
        emit(Resource.Loading())

        //READ THE CURRENT WORDS FROM THE DATABASE - CACHED ONES
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try{
            //INICIATE THE API CALL
            val remoteWordInfos = api.getWordInfo(word)
            //REPLACE THE ITEMS IN THE DATABASE WITH THE RESULTS FROM THE API
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        }catch (e: HttpException){
            emit(Resource.Error(message ="Something went wrong!", data = wordInfos))
        }catch(e: IOException){
            emit(Resource.Error(message ="Couldnt reach server, check your internet conection!", data = wordInfos))
        }

        //IF THERES NO ERRORS WE EMIT THE SUCCESS RESOURCE WITH THW WRORDS FROM THE DATABSE
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}