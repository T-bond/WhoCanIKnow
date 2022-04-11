package hu.t_bond.whocaniknow.component.network.model

data class ResultInfo(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)
