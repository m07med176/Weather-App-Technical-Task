package com.alamiya.weatherapptask.domain.utils

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun entityFromMap(domainModel: DomainModel): Entity
}