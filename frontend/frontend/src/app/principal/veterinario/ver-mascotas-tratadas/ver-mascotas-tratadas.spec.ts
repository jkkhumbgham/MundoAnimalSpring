import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerMascotasTratadas } from './ver-mascotas-tratadas';

describe('VerMascotasTratadas', () => {
  let component: VerMascotasTratadas;
  let fixture: ComponentFixture<VerMascotasTratadas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VerMascotasTratadas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerMascotasTratadas);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
