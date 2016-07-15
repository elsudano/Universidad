/* 
 * File:   Persona.h
 *
 * Created on 24 de diciembre de 2015, 11:48
 */

Persona::Persona(){
    this->num = 0;
    this->nombre = "";
    this->path_photo = "";
    this->codigo = "";
}

Persona::Persona(int num, string nom, string path){
    this->num = num;
    this->nombre = nom;
    this->path_photo = path;
}

Persona::Persona(int num, string nom, string path, string cod){
    this->num = num;
    this->nombre = nom;
    this->path_photo = path;
    this->codigo = cod;
}

Persona::Persona(const Persona &p){
    this->num = p.num;
    this->nombre = p.nombre;
    this->path_photo = p.path_photo;
    this->codigo = p.codigo;
}

Persona::~Persona(){
    this->nombre.clear();
    this->path_photo.clear();
    this->codigo.clear();
}

int Persona::GetNumero()const{
    return this->num;
}

string Persona::GetNombre()const{
    return this->nombre;
}

string Persona::GetPath()const {
    return this->path_photo;
}

string Persona::GetCodigo()const {
    return this->codigo;
}

void Persona::SetCodigoRespuestas(string &cod){
    this->codigo = cod;
}

std::ostream &operator<<(std::ostream &out, const Persona &per){
    out << per.num << " Nombre: " << per.nombre << " Imagen: " << per.path_photo << " Codigo: " << per.codigo;
    return out;
}