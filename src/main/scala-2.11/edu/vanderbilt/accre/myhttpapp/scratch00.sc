
val baseuri = "http://api.elsevier.com/content/%s/article/doi/%s"
val doi = "10.1016/j.cemconres.2014.03.007"

val foo = "a;lsdkjf"
println(baseuri.format(foo, doi))
