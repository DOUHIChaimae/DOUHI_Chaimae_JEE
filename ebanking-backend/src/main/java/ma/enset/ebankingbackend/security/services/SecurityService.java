package ma.enset.ebankingbackend.security.services;

import ma.enset.ebankingbackend.security.entities.AppRole;
import ma.enset.ebankingbackend.security.entities.AppUser;

public interface SecurityService {
AppUser saveNewUser(String userName, String password, String rePssword);
AppRole saveNewRole(String roleName, String description);
void addRoleToUser(String userName,String roleName);
AppUser loadUserByUserName(String userName);


}
