import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessOverlay } from './success-overlay';

describe('SuccessOverlay', () => {
  let component: SuccessOverlay;
  let fixture: ComponentFixture<SuccessOverlay>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuccessOverlay]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuccessOverlay);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
