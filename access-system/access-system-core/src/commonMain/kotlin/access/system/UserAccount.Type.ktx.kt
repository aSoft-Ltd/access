package access.system

import access.account.Account

fun Account.Type.permissions() = permissionGroups.flatMap { it.permissions }

fun Account.Type.permissionNames() = permissions().map { it.title }

fun List<Account.Type>.permissionGroups() = flatMap { it.permissionGroups }