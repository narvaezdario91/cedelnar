package com.narvaezjdario

import grails.plugin.springsecurity.annotation.Secured

class ProfileController {

	def springSecurityService
	
	@Secured(value=["hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"])
    def index() { 
		def userInstance = springSecurityService.currentUser
		model:[userInstance:userInstance]
	}
	@Secured(value=["hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"])
	def editUser(){
		def roleInstance = Role.findByAuthority('ROLE_USER')
		def userInstance = User.get(params.idUser)
		userInstance.properties = params
		if (!userInstance.save(flush:true)) {
			render template:'../errorInstance', model:[errorInstance:userInstance], status:400
			return
		}
		
		
		UserRole.create(userInstance, roleInstance, true)
		render(contentType: 'text/json') {[
			'message': 'Actualización Exitosa',
		    'fullName': userInstance.fullName,
			'email': userInstance.email,
		    'status': 200
		]}
	}
}
