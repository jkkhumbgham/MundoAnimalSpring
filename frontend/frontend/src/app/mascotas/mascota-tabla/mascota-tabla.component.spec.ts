import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MascotaTabla } from './mascota-tabla.component';

describe('MascotaTabla', () => {
  let component: MascotaTabla;
  let fixture: ComponentFixture<MascotaTabla>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MascotaTabla]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MascotaTabla);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
