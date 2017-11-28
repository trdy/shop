
###Spring Boot + Vue 

###DataBase

######db script
    create database demo;
    
    use demo;
    
    create table person(pid int primary key auto_increment,pname varchar(50),birth timestamp,pimg varchar(100));

###JPA示例

    public interface TestDBJPA extends JpaSpecificationExecutor<UserInfo>,PagingAndSortingRepository<UserInfo,Long> {

    @Query(value = "from UserInfo u where u.userName=:userName and u.userPass=:userPass")
    UserInfo findUserInfoByNameAndPass(@Param("userName") String userName, @Param("userPass") String userPass);

    }
    
###JDBCTemplate示例
    @Repository("testDBDao")
    public class TestDBDao {
    
        @PersistenceContext
        private EntityManager entityManager;
    
        @Autowired
        private JdbcTemplate jdbcTemplate;
    
        public List<UserInfo> findUserInfoList() {
            String sql="select * from tab_user";
            return jdbcTemplate.query(sql, new RowMapper<UserInfo>() {
                @Override
                public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                    UserInfo u=new UserInfo();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUserName(rs.getString("user_name"));
                    u.setUserPass(rs.getString("user_pass"));
                    u.setBirth(rs.getTimestamp("birth"));
                    return u;
                }
            });
        }
    
        public List<UserInfo> findUserInfoList2(){
            Query query=entityManager.createQuery("from UserInfo u");
            
            return query.getResultList();
        }
    }