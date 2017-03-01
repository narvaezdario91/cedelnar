import com.narvaezjdario.Role
import com.narvaezjdario.User
import com.narvaezjdario.UserRole

class BootStrap {

    def init = { servletContext ->
		
		def adminRole = new Role('ROLE_ADMIN').save()
		def userRole = new Role('ROLE_USER').save()
  
		def testUser = new User('me', 'password').save()
  
		UserRole.create(testUser, adminRole, true)
    }
    def destroy = {
    }
}
