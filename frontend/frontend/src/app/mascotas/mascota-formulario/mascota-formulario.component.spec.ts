import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MascotaFormularioComponent } from './mascota-formulario.component';

describe('MascotaFormulario', () => {
  let component: MascotaFormularioComponent;
  let fixture: ComponentFixture<MascotaFormularioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MascotaFormularioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MascotaFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
