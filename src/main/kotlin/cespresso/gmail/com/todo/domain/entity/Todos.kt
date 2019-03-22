package cespresso.gmail.com.todo.domain.entity

import javax.persistence.*

@Entity
@Table(name = "todos")
class Todos (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    var id: Long? = null,
    @Column(name="user_id")
    var user_id: String? = null,
    @Column(name="title")
    var title: String? = null,
    @Column(name="completed")
    val completed:Boolean? =null,
    @Column(name="body")
    var body: String? = null

){
    constructor(_title: String?,_completed: Boolean?,_body: String?):this(title = _title,completed = _completed,body = _body)
}