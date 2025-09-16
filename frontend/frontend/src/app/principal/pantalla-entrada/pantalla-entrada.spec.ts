import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PantallaEntrada } from './pantalla-entrada';

describe('PantallaEntrada', () => {
  let component: PantallaEntrada;
  let fixture: ComponentFixture<PantallaEntrada>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PantallaEntrada]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PantallaEntrada);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
