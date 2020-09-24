import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Attribute } from 'src/app/class/attribute'

@Injectable({
  providedIn: 'root'
})
export class AttributeService {

  private attributesUri = "https://epol-gatewayapi.xfarm.xyz/attributes";

  constructor(private _httpClient: HttpClient) { }

  getAllAttributes(): Observable<Attribute[]>  {
      return this._httpClient.get<Attribute[]>(this.attributesUri);
  }

  getAttributeByName(name): Observable<Attribute> {
    const uri = `${this.attributesUri}/${name}`;
    return this._httpClient.get<Attribute>(uri);
  }

  saveAttribute(attribute): Observable<any> {
    const httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    }
    const uri = `${this.attributesUri}/save`;
    return this._httpClient.post<any>(uri, attribute, httpOptions);
  }

  updateAttribute(attribute): Observable<any> {
    const uri = `${this.attributesUri}/update`;
    return this._httpClient.put<any>(uri, attribute);
  }

  deleteAttribute(name): Observable<any> {
    const uri = `${this.attributesUri}/delete/${name}`;
    return this._httpClient.delete<any>(uri);
  }
}
