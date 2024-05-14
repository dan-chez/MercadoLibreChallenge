package com.danchez.mercadolibrechallenge.domain.mapper

interface ModelMapper<I, O> {
    fun map(input: I): O
}
