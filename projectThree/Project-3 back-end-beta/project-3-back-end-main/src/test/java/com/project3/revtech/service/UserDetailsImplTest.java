package com.project3.revtech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project3.revtech.entity.ERole;
import com.project3.revtech.entity.Role;
import com.project3.revtech.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserDetailsImplTest {
    @Test
    void testConstructor() {
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        UserDetailsImpl actualUserDetailsImpl = new UserDetailsImpl(1, "janedoe", "jane.doe@example.org", "iloveyou",
                grantedAuthorityList, "Jane", "Doe", "42 Main St", "Contact");
        actualUserDetailsImpl.setAddress("42 Main St");
        ArrayList<GrantedAuthority> grantedAuthorityList1 = new ArrayList<>();
        actualUserDetailsImpl.setAuthorities(grantedAuthorityList1);
        actualUserDetailsImpl.setContact("Contact");
        actualUserDetailsImpl.setEmail("jane.doe@example.org");
        actualUserDetailsImpl.setFirst_name("Jane");
        actualUserDetailsImpl.setLast_name("Doe");
        actualUserDetailsImpl.setPassword("iloveyou");
        actualUserDetailsImpl.setUser_id(1);
        actualUserDetailsImpl.setUsername("janedoe");
        assertEquals("42 Main St", actualUserDetailsImpl.getAddress());
        Collection<? extends GrantedAuthority> authorities = actualUserDetailsImpl.getAuthorities();
        assertSame(grantedAuthorityList1, authorities);
        assertEquals(grantedAuthorityList, authorities);
        assertEquals("Contact", actualUserDetailsImpl.getContact());
        assertEquals("jane.doe@example.org", actualUserDetailsImpl.getEmail());
        assertEquals("Jane", actualUserDetailsImpl.getFirst_name());
        assertEquals("Doe", actualUserDetailsImpl.getLast_name());
        assertEquals("iloveyou", actualUserDetailsImpl.getPassword());
        assertEquals(1, actualUserDetailsImpl.getUser_id());
        assertEquals("janedoe", actualUserDetailsImpl.getUsername());
        assertTrue(actualUserDetailsImpl.isAccountNonExpired());
        assertTrue(actualUserDetailsImpl.isAccountNonLocked());
        assertTrue(actualUserDetailsImpl.isCredentialsNonExpired());
        assertTrue(actualUserDetailsImpl.isEnabled());
    }

    @Test
    void testBuild() {
        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUser_id(1);
        user.setUsername("janedoe");
        UserDetailsImpl actualBuildResult = UserDetailsImpl.build(user);
        assertEquals("42 Main St", actualBuildResult.getAddress());
        assertEquals("janedoe", actualBuildResult.getUsername());
        assertEquals(1, actualBuildResult.getUser_id());
        assertEquals("iloveyou", actualBuildResult.getPassword());
        assertEquals("Doe", actualBuildResult.getLast_name());
        assertEquals("Jane", actualBuildResult.getFirst_name());
        assertEquals("jane.doe@example.org", actualBuildResult.getEmail());
        assertEquals("Contact", actualBuildResult.getContact());
        assertTrue(actualBuildResult.getAuthorities().isEmpty());
    }

    @Test
    void testBuild2() {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(roleSet);
        user.setUser_id(1);
        user.setUsername("janedoe");
        UserDetailsImpl actualBuildResult = UserDetailsImpl.build(user);
        assertEquals("42 Main St", actualBuildResult.getAddress());
        assertEquals("janedoe", actualBuildResult.getUsername());
        assertEquals(1, actualBuildResult.getUser_id());
        assertEquals("iloveyou", actualBuildResult.getPassword());
        assertEquals("Doe", actualBuildResult.getLast_name());
        assertEquals("Jane", actualBuildResult.getFirst_name());
        assertEquals("jane.doe@example.org", actualBuildResult.getEmail());
        assertEquals("Contact", actualBuildResult.getContact());
        Collection<? extends GrantedAuthority> authorities = actualBuildResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
    }

    @Test
    void testBuild3() {
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_USER);

        Role role1 = new Role();
        role1.setId(1);
        role1.setName(ERole.ROLE_USER);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        roleSet.add(role);

        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(roleSet);
        user.setUser_id(1);
        user.setUsername("janedoe");
        UserDetailsImpl actualBuildResult = UserDetailsImpl.build(user);
        assertEquals("42 Main St", actualBuildResult.getAddress());
        assertEquals("janedoe", actualBuildResult.getUsername());
        assertEquals(1, actualBuildResult.getUser_id());
        assertEquals("iloveyou", actualBuildResult.getPassword());
        assertEquals("Doe", actualBuildResult.getLast_name());
        assertEquals("Jane", actualBuildResult.getFirst_name());
        assertEquals("jane.doe@example.org", actualBuildResult.getEmail());
        assertEquals("Contact", actualBuildResult.getContact());
        Collection<? extends GrantedAuthority> authorities = actualBuildResult.getAuthorities();
        assertEquals(2, authorities.size());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(0).toString());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) authorities).get(1).toString());
    }

    @Test
    void testEquals() {
        assertFalse(UserDetailsImpl.build(new User()).equals(null));
        assertFalse(UserDetailsImpl.build(new User()).equals("Different type to UserDetailsImpl"));
    }

    @Test
    void testEquals2() {
        UserDetailsImpl buildResult = UserDetailsImpl.build(new User());
        assertTrue(buildResult.equals(buildResult));
        int expectedHashCodeResult = buildResult.hashCode();
        assertEquals(expectedHashCodeResult, buildResult.hashCode());
    }

    @Test
    void testEquals3() {
        UserDetailsImpl buildResult = UserDetailsImpl.build(new User());
        UserDetailsImpl buildResult1 = UserDetailsImpl.build(new User());
        assertTrue(buildResult.equals(buildResult1));
        int notExpectedHashCodeResult = buildResult.hashCode();
        assertFalse(Objects.equals(notExpectedHashCodeResult, buildResult1.hashCode()));
    }

    @Test
    void testEquals4() {
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(1, "janedoe", "jane.doe@example.org", "iloveyou", null,
                "Jane", "Doe", "42 Main St", null);
        assertFalse(userDetailsImpl.equals(UserDetailsImpl.build(new User())));
    }
}

