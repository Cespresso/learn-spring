package cespresso.gmail.com.todo.domain.entity

import javax.persistence.*

@Entity
@Table(name = "todos")
class Todos (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    var id: Long = 0,
    @Column(name="title")
    var title: String = "",
    @Column(name="description")
    val description:String ="",
    @Column(name="body")
    var body: String = ""

)