package cespresso.gmail.com.todo.domain.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    var id: Long = 0,
    @Column(name="title")
    var name: String = ""
//    @OneToMany(mappedBy = "user")// リレーション先(ManyToOne)のプロパティを指定する
//    var todosList:List<Todos>
)