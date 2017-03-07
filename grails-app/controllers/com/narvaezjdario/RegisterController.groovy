package com.narvaezjdario
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class RegisterController {

    def create() { 
		def roleInstance = Role.findByAuthority('ROLE_USER')
		def userInstance = new User(params)
		if (!userInstance.save(flush:true)) {
			render template:'../errorInstance', model:[errorInstance:userInstance], status:400
			return
		}
		
		
		UserRole.create(userInstance, roleInstance, true)
		render 'Registro Exitoso', status:201
	}
}
