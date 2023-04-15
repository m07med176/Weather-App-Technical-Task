package com.alamiya.weatherapptask.domain.models

import com.alamiya.weatherapptask.data.source.dto.countries.CountriesItem
import com.alamiya.weatherapptask.domain.utils.EntityMapper

class CountriesMapper: EntityMapper<List<CountriesItem>,List<RegionsName>> {
    override fun mapFromEntity(entity: List<CountriesItem>): List<RegionsName> {
        val regions:MutableList<RegionsName> = mutableListOf()
        entity.forEach{
            regions.add(RegionsName(it.name))
            it.states.map {stete ->
                regions.add(RegionsName(stete.name))
            }
        }
        return regions
    }

}