package com.stc.filesSystem;


import com.stc.filesSystem.enums.PermissionLevel;
import com.stc.filesSystem.model.Permission;
import com.stc.filesSystem.model.PermissionGroup;
import com.stc.filesSystem.service.interfaces.PermissionGroupService;
import com.stc.filesSystem.service.interfaces.PermissionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FilesSystemApplication {

	public static void main(String[] args)  {
		SpringApplication.run(FilesSystemApplication.class, args);

	}



//    @Bean
//	CommandLineRunner initPermissionGroups(PermissionGroupService permissionGroupService) {
//        return args -> {
//            permissionGroupService.savePermissionGroup( PermissionGroup.builder().name("ADMIN").build());
//            permissionGroupService.savePermissionGroup( PermissionGroup.builder().name("USER").build());
//
//        };
//    }

//    @Bean
//	CommandLineRunner initPermissions(PermissionService permissionService) {
//        return args -> {
//            
//            permissionService.savePermission( Permission.builder().userEmail("userWithEditPrivilege@Stc.com").permissionLevel(PermissionLevel.EDIT).build());
//
//        };
//    }


}
