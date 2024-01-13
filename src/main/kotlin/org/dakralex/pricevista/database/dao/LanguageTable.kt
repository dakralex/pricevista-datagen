package org.dakralex.pricevista.database.dao

import org.dakralex.pricevista.contracts.dao.LanguageDao
import org.dakralex.pricevista.contracts.database.Database
import org.dakralex.pricevista.entities.Language
import org.dakralex.pricevista.entities.LanguageId
import org.dakralex.pricevista.entities.data.ELanguage
import java.sql.ResultSet

class LanguageTable(
    db: Database
) : LanguageDao,
    DatabaseTable<Language, LanguageId>(
        db,
        "Language",
        listOf("id", "alpha2", "alpha3", "name")
    ) {

    override fun initialize(): Boolean {
        if (super.initialize()) {
            addBatch(ELanguage.entries.asSequence().map(ELanguage::language))
            return true
        }

        return false
    }

    override fun isUnique(entity: Language): (Language) -> Boolean {
        return { e ->
            e.id == entity.id ||
                    e.alpha2 == entity.alpha2 &&
                    e.alpha3 == entity.alpha3 &&
                    e.name == entity.name
        }
    }

    override fun matchesWithId(id: LanguageId): (Language) -> Boolean {
        return { e -> e.id == id }
    }

    override fun mapFromResultSet(resultSet: ResultSet): Language {
        return Language(
            resultSet.getInt("id"),
            resultSet.getString("alpha2"),
            resultSet.getString("alpha3"),
            resultSet.getString("name")
        )
    }

    override fun mapToPropArray(entry: Language): Array<Any?> {
        return arrayOf(
            entry.id,
            entry.alpha2,
            entry.alpha3,
            entry.name
        )
    }
}