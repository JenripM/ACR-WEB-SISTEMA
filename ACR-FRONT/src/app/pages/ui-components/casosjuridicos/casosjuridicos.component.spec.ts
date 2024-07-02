import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasosjuridicosComponent } from './casosjuridicos.component';

describe('CasosjuridicosComponent', () => {
  let component: CasosjuridicosComponent;
  let fixture: ComponentFixture<CasosjuridicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CasosjuridicosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CasosjuridicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
