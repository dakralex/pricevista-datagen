package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.ArticleUnitDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.ArticleUnit
import org.dakralex.pricevista.entities.ArticleUnitId
import org.dakralex.pricevista.entities.data.EArticleUnit
import java.sql.ResultSet

class ArticleUnitTable(
    db: Database
) : ArticleUnitDao,
    DatabaseTable<ArticleUnit, ArticleUnitId>(
        db,
        "Article_Unit",
        listOf("id", "label", "singular_name", "plural_name")
    ) {

    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(EArticleUnit.entries.asSequence().map(EArticleUnit::unit))
            return true
        }

        return false
    }

    override fun isUnique(entity: ArticleUnit): (ArticleUnit) -> Boolean {
        return { e ->
            e.id == entity.id ||
                    e.label == entity.label &&
                    e.singularName == entity.singularName &&
                    e.pluralName == entity.pluralName
        }
    }

    override fun matchesWithId(id: ArticleUnitId): (ArticleUnit) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): ArticleUnit {
        return ArticleUnit(
            resultSet.getInt("id"),
            resultSet.getString("label"),
            resultSet.getString("singular_name"),
            resultSet.getString("plural_name")
        )
    }

    override fun mapToPropArray(entry: ArticleUnit): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.label,
            entry.singularName,
            entry.pluralName
        )
    }

}