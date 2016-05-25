package fin.info.server.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name="user")
public class User implements Serializable {

	private String id;

	private String name;

	private LocalDate joiningDate;
	
    private Set<SearchItem> searchItems;
	
	private Set<Authority> authorities;

	@Id
//	@GenericGenerator(name = "generator", strategy = "uuid.hex")   
//	@GeneratedValue(generator = "generator") 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = true, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DateTimeFormat(pattern="yyyy/MM/dd") 
	@Column(name = "join_date", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}


	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	public Set<SearchItem> getSearchItems() {
		return searchItems;
	}

	public void setSearchItems(Set<SearchItem> searchItems) {
		this.searchItems = searchItems;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", 
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id") })
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
	
	

}
