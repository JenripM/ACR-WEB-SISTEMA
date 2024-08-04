import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PredictService {
  private apiUrl = 'http://localhost:5000/predict';

  constructor(private http: HttpClient) { }

  predict(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }



  savePrediction(prediction: any): Observable<any> {
    return this.http.post<any>(`http://localhost:5000/api/v1/prediccion/save`, prediction);
  }
}
