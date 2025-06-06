package me.vpatel.db.tables;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import me.vpatel.network.api.ConvoUser;

import java.time.OffsetDateTime;
import java.util.UUID;

public class UsersTable {

    private long id;
    private String uuid;
    private String name;
    private String passwordHash;
    private String salt;
    private OffsetDateTime timestamp;

    public UsersTable() {

    }

    public UsersTable(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public static UsersTable of(ConvoUser user) {
        UsersTable usersTable = new UsersTable(user.getId().toString(), user.getName());
        if (user.equals(ConvoUser.AI_USER)) {
            usersTable.setId(ConvoUser.AI_USER.getInternalId());
        } else {
            usersTable.setId(user.getInternalId());
        }
        return usersTable;
    }

    public ConvoUser convert() {
        ConvoUser convoUser = new ConvoUser(UUID.fromString(uuid), name);
        convoUser.setInternalId(id);
        return convoUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersTable that = (UsersTable) o;
        return id == that.id &&
                Objects.equal(uuid, that.uuid) &&
                Objects.equal(name, that.name) &&
                Objects.equal(passwordHash, that.passwordHash) &&
                Objects.equal(salt, that.salt) &&
                Objects.equal(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, uuid, name, passwordHash, salt, timestamp);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("uuid", uuid)
                .add("name", name)
                .add("timestamp", timestamp)
                .toString();
    }

}
