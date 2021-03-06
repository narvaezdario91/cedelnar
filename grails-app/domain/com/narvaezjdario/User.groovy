package com.narvaezjdario

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	String email
	String fullName
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Picture photo

	User(String username, String password, String email, String fullName) {
		this()
		this.username = username
		this.password = password
		this.email = email
		this.fullName = fullName
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank:false, email:true
		fullName blank: false
		photo nullable: true, blank:true
	}

	static mapping = {
		password column: '`password`'
	}
}
