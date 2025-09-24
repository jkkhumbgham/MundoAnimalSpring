import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceCards } from './service-cards';

describe('ServiceCards', () => {
  let component: ServiceCards;
  let fixture: ComponentFixture<ServiceCards>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ServiceCards]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceCards);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
