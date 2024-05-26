package jp.co.axa.apidemo.security;

/**
 * Represents a role for a user.
 * <p>
 * The {@code Role} class is an enumeration that defines the available roles for a user.
 * It provides the following roles:
 * <ul>
 *     <li>{@code EMPLOYEE}: Represents a regular employee role.</li>
 *     <li>{@code MANAGER}: Represents a manager role.</li>
 *     <li>{@code ADMIN}: Represents an admin role.</li>
 * </ul>
 */
public enum Role {
    EMPLOYEE,
    MANAGER,
    ADMIN
}
